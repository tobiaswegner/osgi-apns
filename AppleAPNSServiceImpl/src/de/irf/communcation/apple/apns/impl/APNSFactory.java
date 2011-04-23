//    osgi-apns OSGi implemenation for apple's push notification service
//    Copyright (C) 2011 Tobias Wegner
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package de.irf.communcation.apple.apns.impl;

import java.io.FileInputStream;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;

import de.irf.communication.apple.apns.APNS;

public class APNSFactory {
	class ServiceInfo {
		String id;
		ServiceRegistration serviceRegistration;
		APNS apns;
	}
	protected Hashtable<String, ServiceInfo> registeredServices = new Hashtable<String, ServiceInfo>();
	
	protected ComponentContext context;
	
	public void activate (ComponentContext context)
	{
		this.context = context;
		
		//load configuration
		String configFileName = System.getProperty("apns.config");
		
		if (configFileName != null)
		{
			Properties configProperties = new Properties();
			
			try
			{
				configProperties.load(new FileInputStream(configFileName));
				
				int configCount = Integer.parseInt(configProperties.getProperty("apns.service.count"));
				
				for (int i = 0; i < configCount; i++)
				{
					String namespace = "apns.service." + i;
					
					String id = configProperties.getProperty(namespace + ".id");
					String cert = configProperties.getProperty(namespace + ".cert");
					String certPassword = configProperties.getProperty(namespace + ".certpwd");
					String sandbox = configProperties.getProperty(namespace + ".sandbox");
					
					ServiceInfo serviceInfo = new ServiceInfo();
					
					serviceInfo.id = id;
					if (sandbox != null)
						serviceInfo.apns = new APNSImpl(cert, certPassword, sandbox.equals("true"));
					else
						serviceInfo.apns = new APNSImpl(cert, certPassword, true);
					
					Dictionary<String, String> serviceProperties = new Hashtable<String, String>();
					
					serviceProperties.put("apns.id", id);
					serviceProperties.put("apns.sandbox", new Boolean(sandbox).toString());
					
					serviceInfo.serviceRegistration = context.getBundleContext().registerService(APNS.class.getName(), serviceInfo.apns, serviceProperties);
					
					registeredServices.put(id, serviceInfo);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void deactivate ()
	{
		for (String key : registeredServices.keySet()) {
			ServiceInfo serviceInfo = registeredServices.get(key);
			
			serviceInfo.serviceRegistration.unregister();
		}
	}
}
