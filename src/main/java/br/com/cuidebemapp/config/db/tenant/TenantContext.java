package br.com.cuidebemapp.config.db.tenant;

import java.util.Locale;

public class TenantContext {

    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();
    private static ThreadLocal<Locale> currentLocale = new ThreadLocal<>();
    
    private TenantContext() {
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.set(null);
        currentLocale.set(null);
    }
    
    public static Locale getCurrentLocale() {
        return currentLocale.get();
    }

    public static void setCurrentLocale(Locale locale) {
        currentLocale.set(locale);
    }

    

}
