package uk.co.redkiteweb.dccweb.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.SystemParameter;
import uk.co.redkiteweb.dccweb.data.repositories.SystemParameterRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shawn on 25/11/16.
 */
@Component
public class SettingsService {

    private SystemParameterRepository systemParameterRepository;
    private final Map<String, SystemParameter> settingsMap;

    public SettingsService() {
        this.settingsMap = new HashMap<String, SystemParameter>();
    }

    @Autowired
    public void setSystemParameterRepository(final SystemParameterRepository systemParameterRepository) {
        this.systemParameterRepository = systemParameterRepository;
    }

    public List<SystemParameter> getAllSettings() {
        return (List<SystemParameter>)systemParameterRepository.findAll();
    }

    public String getSettingValue(final String name, final String defaultValue) {
        SystemParameter systemParameter;
        if (settingsMap.containsKey(name)) {
            systemParameter = settingsMap.get(name);
        } else {
            systemParameter = systemParameterRepository.findByName(name);
            if (systemParameter == null) {
                systemParameter = setSettingValue(name, defaultValue);
            }
            settingsMap.put(name, systemParameter);
        }
        return systemParameter.getValue();
    }

    public SystemParameter setSettingValue(final String name, final String value) {
        final SystemParameter systemParameter = new SystemParameter();
        systemParameter.setName(name);
        systemParameter.setValue(value);
        systemParameterRepository.save(systemParameter);
        return systemParameter;
    }
}
