//    osgi-apns OSGi console command provider for apple's push notification service
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

package de.irf.communication.apple.apns.cmd;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import de.irf.communication.apple.apns.APNS;

public class AppleAPNSCmdProvider implements CommandProvider {

	protected APNS apns = null;
	
	public void bindAPNS(APNS apns) { this.apns = apns; };
	
	@Override
	public String getHelp() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("---Apple Push Notification Service---\r\n");
		sb.append("\tapns - Apple push notification service\r\n");
		
		return sb.toString();
	}

	public void _apns(CommandInterpreter ci)
	{
		String cmd = ci.nextArgument();
		
		if ((cmd == null) || (cmd.equals("help")))
		{
			ci.println(getHelp());
			
			return;
		}
		
		if (cmd.equals("push"))
		{
			String deviceToken = ci.nextArgument();
			String payload = ci.nextArgument();
			
			try {
				apns.push(deviceToken, payload);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
