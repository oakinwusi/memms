/**
 * Copyright (c) 2012, Clinton Health Access Initiative.
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
package org.chai.memms.location

import org.chai.location.DataLocation;
import org.chai.location.DataLocationType;
import org.chai.memms.location.DataLocationController;
import org.chai.location.Location;
import org.chai.memms.Initializer;
import org.chai.memms.IntegrationTests;

/**
 * @author Jean Kahigiso M.
 *
 */
class DataLocationControllerSpec extends IntegrationTests{
	def dataLocationController
	
	def "can create save a dataLocation"(){
		setup:
		setupLocationTree()
		dataLocationController =  new DataLocationController()
		when:
		def type = DataLocationType.findByCode("Health Center")
		def location = Location.findByCode("Burera")
		dataLocationController.params.code = CODE("123")
		dataLocationController.params.names_en = "Test data location"
		dataLocationController.params."type.id" =  type.id
		dataLocationController.params."location.id" = location.id
		dataLocationController.save()
		then:
		DataLocation.count() == 5
		DataLocation.list()[4].code == CODE("123")
	}
	
	def "can edit a dataLocation add/remove manages "(){
		setup:
		setupLocationTree()
		def gitwe = DataLocation.findByCode(GITWE)
		def muvuna = DataLocation.findByCode(MUVUNA)
		def burera =  Location.findByCode(BURERA)
		def butaro =  DataLocation.findByCode(BUTARO)
		butaro.addToManages(gitwe)
		butaro.addToManages(muvuna)
		butaro.save()
		def type = DataLocationType.findByCode("Health Center")
		def ruli = Initializer.newDataLocation(['en':"ruli"], CODE("900"), burera, type)
		dataLocationController =  new DataLocationController()
		when:
		dataLocationController.params."id" = butaro.id
		dataLocationController.params.code = butaro.code
		dataLocationController.params.names_en = "Test data location"
		dataLocationController.params."type.id" =  butaro.type.id
		dataLocationController.params."location.id" = butaro.location.id
		dataLocationController.params."managesIds" = [ruli.id+'',gitwe.id+'']
		dataLocationController.save()
		then:
		DataLocation.count() == 5
		butaro.manages.size() == 2
		butaro.manages.contains(ruli)
		butaro.manages.contains(gitwe)
		!butaro.manages.contains(muvuna)
		gitwe.managedBy == butaro
		muvuna.managedBy == null
		ruli.managedBy == butaro
		
	}



}