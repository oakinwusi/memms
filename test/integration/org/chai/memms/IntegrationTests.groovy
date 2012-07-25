package org.chai.memms


/**
* Copyright (c) 2011, Clinton Health Access Initiative.
*
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*     * Redistributions of source code must retain the above copyright
*       notice, this list of conditions and the following disclaimer.
*     * Redistributions in binary form must reproduce the above copyright
*       notice, this list of conditions and the following disclaimer in the
*       documentation and/or other materials provided with the distribution.
*     * Neither the name of the <organization> nor the
*       names of its contributors may be used to endorse or promote products
*       derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/


import java.util.Date

import javax.servlet.ServletRequest;

import grails.plugin.spock.IntegrationSpec

//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.util.ThreadContext;
//import org.apache.shiro.web.util.WebUtils;
import org.chai.memms.security.User;
import org.chai.memms.security.UserType;

abstract class IntegrationTests extends IntegrationSpec {
	static def newUser(def username, def uuid) {
		return new User(userType: UserType.OTHER, code: username, username: username, permissionString: '', passwordHash:'', uuid: uuid, firstname: 'first', lastname: 'last', organisation: 'org', phoneNumber: '+250 11 111 11 11').save(failOnError: true)
	}
	
	static def newUser(def username, def active, def confirmed) {
		return new User(userType: UserType.OTHER, code: 'not_important', username: username, email: username,
			passwordHash: '', active: active, confirmed: confirmed, uuid: 'uuid', firstname: 'first', lastname: 'last',
			organisation: 'org', phoneNumber: '+250 11 111 11 11').save(failOnError: true)
	}
	
	static def newUser(def username, def passwordHash, def active, def confirmed) {
		return new User(userType: UserType.OTHER, code: 'not_important', username: username, email: username,
			passwordHash: passwordHash, active: active, confirmed: confirmed, uuid: 'uuid', firstname: 'first', lastname: 'last',
			organisation: 'org', phoneNumber: '+250 11 111 11 11').save(failOnError: true)
	}
	
	static def setupLocationTree() {
//		// for the test environment, the location level is set to 4
//		// so we create a tree accordingly
//		def hc = newDataLocationType(j(["en":HEALTH_CENTER_GROUP]), HEALTH_CENTER_GROUP);
//		def dh = newDataLocationType(j(["en":DISTRICT_HOSPITAL_GROUP]), DISTRICT_HOSPITAL_GROUP);
//		
//		def country = newLocationLevel(NATIONAL, 1)
//		def province = newLocationLevel(PROVINCE, 2)
//		def district = newLocationLevel(DISTRICT, 3)
//		def sector = newLocationLevel(SECTOR, 4)
//		
//		def rwanda = newLocation(j(["en":RWANDA]), RWANDA, country)
//		def north = newLocation(j(["en":NORTH]), NORTH, rwanda, province)
//		def burera = newLocation(j(["en":BURERA]), BURERA, north, district)
//		
//		newDataLocation(j(["en":BUTARO]), BUTARO, burera, dh)
//		newDataLocation(j(["en":KIVUYE]), KIVUYE, burera, hc)
	}
}
