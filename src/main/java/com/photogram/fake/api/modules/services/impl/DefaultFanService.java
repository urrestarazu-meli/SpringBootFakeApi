package com.photogram.fake.api.modules.services.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.photogram.fake.api.modules.entities.domain.Post;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.exceptions.BusinessException;
import com.photogram.fake.api.modules.services.FanService;
import com.photogram.fake.api.modules.usecase.AddFan;
import com.photogram.fake.api.modules.usecase.GetMyFans;
import com.photogram.fake.api.modules.usecase.GetsPostsFan;
import com.photogram.fake.api.modules.usecase.ValidateSession;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultFanService implements FanService {
    public static final String REPORT_FORMAT_JSON = "json";
    public static final String REPORT_FORMAT_XML = "xml";
    public static final String REPORTS_FANATICS_JSON = "./reports/fanatics.json";
    public static final String REPORTS_FANATICS_XML = "./reports/fanatics.xml";
    @Autowired
    private GetMyFans getMyFans;

    @Autowired
    private GetsPostsFan getsPostsFan;

    @Autowired
    private AddFan addFan;

    @Autowired
    ValidateSession validateSession;

    /**
     * {@inheritDoc}
     */
    public User add(Model model) {
        return addFan.add(AddFan.Model.builder()
                .userId(model.getUserId())
                .build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> get(Model model) {
        validateSession(model);

        return getMyFans.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getPostsFan(Model model) {
        validateSession(model);

        return getsPostsFan.get(GetsPostsFan.Model.builder()
                .userId(model.getUserId())
                .build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void report(Model model) {
        validateSession(model);

        List<User> fans = getMyFans.get();

        if (REPORT_FORMAT_JSON.equalsIgnoreCase(model.getFormat())) {
            generateJsonReport(fans);

        } else if (REPORT_FORMAT_XML.equalsIgnoreCase(model.getFormat())) {
            generateXmlReport(fans);

        } else {
            throw new BusinessException("The report format is invalid.");
        }
    }

    private void generateXmlReport(List<User> fans) {
        File file = new File(REPORTS_FANATICS_XML);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(REPORTS_FANATICS_XML)) {
            XmlMapper xmlMapper = new XmlMapper();

            String xmlString = xmlMapper.writeValueAsString(fans);
            log.info(xmlString);

            writer.write(xmlString);
        } catch (IOException exc) {
            throw new BusinessException("XML report could not be generated.", exc);
        }
    }

    private void generateJsonReport(List<User> fans) {
        File file = new File(REPORTS_FANATICS_JSON);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file)) {

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            gson.toJson(fans, writer);
        } catch (IOException exc) {
            throw new BusinessException("JSON report could not be generated.", exc);
        }

    }

    /*
    Validates a user session

     * @param model a dan service model
     */
    private void validateSession(Model model) {
        validateSession.validate(ValidateSession.Model.builder()
                .sessionToken(model.getToken())
                .build());
    }
}
