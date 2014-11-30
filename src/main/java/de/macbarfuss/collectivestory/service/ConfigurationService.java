package de.macbarfuss.collectivestory.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.macbarfuss.collectivestory.model.AbstractConfiguration;
import de.macbarfuss.collectivestory.model.repository.ConfigRepository;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigRepository configRepository;

    public ConfigurationService() {
    }

    public AbstractConfiguration get(final Class<? extends AbstractConfiguration> configClass) {
        AbstractConfiguration result = configRepository.findByName(configClass.getName());
        if (result == null) {
            try {
                result = configClass.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        if (result != null) {
            final Boolean dirty = result.fillWithDefaults();
            if (dirty) {
                configRepository.save(result);
            }
        }
        return result;
    }
}
