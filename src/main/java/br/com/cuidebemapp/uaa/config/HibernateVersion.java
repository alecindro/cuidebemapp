package br.com.cuidebemapp.uaa.config;

import org.springframework.util.ClassUtils;

/**
 * Supported Hibernate versions.
 *
 * @author Phillip Webb
 */
enum HibernateVersion {

	/**
	 * Version 4.
	 */
	V4,

	/**
	 * Version 5.
	 */
	V5;

	private static final String HIBERNATE_5_CLASS = "org.hibernate.boot.model."
			+ "naming.PhysicalNamingStrategy";

	private static HibernateVersion running;

	public static HibernateVersion getRunning() {
		if (running == null) {
			setRunning(ClassUtils.isPresent(HIBERNATE_5_CLASS, null) ? V5 : V4);
		}
		return running;
	}

	static void setRunning(HibernateVersion running) {
		HibernateVersion.running = running;
	}

}
