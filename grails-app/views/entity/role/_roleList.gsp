<table class="items">
	<thead>
		<tr>
			<th/>
			<g:sortableColumn property="${i18nField(field: 'names')}" params="[q:q]" title="${message(code: 'roles.label')}" />
			<th><g:message code="role.permissions.label"/></th>
		</tr>
	</thead>
	<tbody>
	<g:if test="${entities != null && !entities.empty}">
		<g:each in="${entities}" status="i" var="role">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<a href="${createLinkWithTargetURI(controller:'role', action:'edit', params:[id: role.id])}" class="edit-button">
								<g:message code="default.link.edit.label" />
							</a>
						</li>
						<li>
							<a href="${createLinkWithTargetURI(controller:'role', action:'delete', params:[id: role.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
						</li>
						
					</ul>
				</td>
				<td>
					${role?.name}
				</td>
				<td><g:stripHtml field="${role?.permissionString}" chars="30"/></td>
			</tr>
		</g:each>
	</g:if>
	<g:else>
		<tr>
			<td><g:message code="entity.list.empty.label" args="[message(code:'role.label')]"/></td>
		</tr>
	</g:else>
	</tbody>
</table>
<g:render template="/templates/pagination" model="[entities:entities, entityCount:entities.totalCount]" />