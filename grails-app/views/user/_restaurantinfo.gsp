<tr class="prop">
                            <td valign="top" class="name">
                                <label for="restaurant"><g:message code="user.restaurant.label" default="Restaurant" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'restaurant', 'errors')}">
                                <%--g:hiddenField type="hidden" name="restaurant.id" value="${userInstance?.restaurant.id}" --%>
                                ${userInstance?.restaurant?.encodeAsHTML()}
                            </td>
                        </tr>