                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="company"><g:message code="user.company.label" default="Company" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'company', 'errors')}">
                                <%--g:hiddenField type="hidden" name="company.id" value="${userInstance?.company.id}" /--%>
                                ${userInstance?.company?.encodeAsHTML()}
                            </td>
                        </tr>