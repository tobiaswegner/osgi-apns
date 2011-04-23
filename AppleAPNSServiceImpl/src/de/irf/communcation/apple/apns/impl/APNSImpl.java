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
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.notnoop.apns.ApnsService;
import com.notnoop.apns.ApnsServiceBuilder;

import de.irf.communication.apple.apns.APNS;

public class APNSImpl implements APNS {
	ApnsService apns = null;
	
	protected String certFileName;
	protected String certPassword;
	protected boolean sandbox;
	
	public APNSImpl (String certFileName, String certPassword, boolean sandbox) throws FileNotFoundException
	{
		this.certFileName = certFileName;
		this.certPassword = certPassword;
		this.sandbox = sandbox;
		
		InputStream certInputStream = new FileInputStream(certFileName);
		
		ApnsServiceBuilder serviceBuilder = com.notnoop.apns.APNS.newService().withCert(certInputStream, certPassword);
		
		if (sandbox)
			serviceBuilder = serviceBuilder.withSandboxDestination();
		
		apns = serviceBuilder.build();
	}
	
	@Override
	public void push(String deviceToken, String payload) throws Exception {
		if (apns != null)
			apns.push(deviceToken, payload);
	}

	@Override
	public void push(String deviceToken, String payload, Date expiry)
			throws Exception {
		if (apns != null)
			apns.push(deviceToken, payload, expiry);
	}

	@Override
	public void push(byte[] deviceToken, byte[] payload) throws Exception {
		if (apns != null)
			apns.push(deviceToken, payload);
	}

	@Override
	public void push(byte[] deviceToken, byte[] payload, int expiry)
			throws Exception {
		if (apns != null)
			apns.push(deviceToken, payload, expiry);
	}

	@Override
	public void push(Collection<String> deviceTokens, String payload)
			throws Exception {
		if (apns != null)
			apns.push(deviceTokens, payload);
	}

	@Override
	public void push(Collection<String> deviceTokens, String payload,
			Date expiry) throws Exception {
		if (apns != null)
			apns.push(deviceTokens, payload, expiry);
	}

	@Override
	public void push(Collection<byte[]> deviceTokens, byte[] payload)
			throws Exception {
		if (apns != null)
			apns.push(deviceTokens, payload);
	}

	@Override
	public void push(Collection<byte[]> deviceTokens, byte[] payload, int expiry)
			throws Exception {
		if (apns != null)
			apns.push(deviceTokens, payload, expiry);
	}

	@Override
	public Map<String, Date> getInactiveDevices() throws Exception {
		if (apns != null)
			return apns.getInactiveDevices();

		return null;
	}

	@Override
	public void testConnection() throws Exception {
		if (apns != null)
			apns.testConnection();
	}

}
