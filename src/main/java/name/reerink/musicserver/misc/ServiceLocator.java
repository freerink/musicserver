package name.reerink.musicserver.misc;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ServiceLocator {
	public static Object getService(String appName, String serviceName) {
		FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext(
				"config/" + appName + "Context.xml");
		return appContext.getBean(serviceName);
	}

}
