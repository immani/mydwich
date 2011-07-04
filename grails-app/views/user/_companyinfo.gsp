                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="company"><g:message code="user.company.label" default="Company" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'company', 'errors')}">
                                ${userInstance?.company?.encodeAsHTML()}
                            </td>
                        </tr>