//    osgi-apns OSGi interface for apple's push notification service
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

package de.irf.communication.apple.apns;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface APNS {
    void push(String deviceToken, String payload) throws Exception;
    void push(String deviceToken, String payload, Date expiry) throws Exception;

    void push(byte[] deviceToken, byte[] payload) throws Exception;
    void push(byte[] deviceToken, byte[] payload, int expiry) throws Exception;

    void push(Collection<String> deviceTokens, String payload) throws Exception;
    void push(Collection<String> deviceTokens, String payload, Date expiry) throws Exception;

    void push(Collection<byte[]> deviceTokens, byte[] payload) throws Exception;
    void push(Collection<byte[]> deviceTokens, byte[] payload, int expiry) throws Exception;

    Map<String, Date> getInactiveDevices() throws Exception;

    void testConnection() throws Exception;
}

