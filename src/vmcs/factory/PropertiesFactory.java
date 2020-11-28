package vmcs.factory;

import java.util.Properties;

public class PropertiesFactory {

    public static final String COIN = "COIN";
    public static final String DRINK = "DRINK";
    public static final String MACHINE = "MACHINE";

    public Properties getProperty(String propertyType) {
        if (propertyType == null) {
            return null;
        }
        if (propertyType.equalsIgnoreCase(COIN)) {
            return new CoinProperties().getProperties();

        } else if (propertyType.equalsIgnoreCase(DRINK)) {
            return new DrinkProperties().getProperties();

        } else if (propertyType.equalsIgnoreCase(MACHINE)) {
            return new MachineProperties().getProperties();
        }
        return null;
    }

    public void saveProperties(String propertyType, Properties properties) {
        if (propertyType.equalsIgnoreCase(COIN)) {
            new CoinProperties().saveProperties(properties);

        } else if (propertyType.equalsIgnoreCase(DRINK)) {
            new DrinkProperties().saveProperties(properties);

        } else if (propertyType.equalsIgnoreCase(MACHINE)) {
            new MachineProperties().saveProperties(properties);
        }
    }

}
