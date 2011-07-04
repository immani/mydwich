<tr class="prop">
                            <td valign="top" class="name">
                                <label for="restaurant"><g:message code="user.restaurant.label" default="Restaurant" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: userInstance, field: 'restaurant', 'errors')}">
                                ${userInstance?.restaurant?.encodeAsHTML()}
                            </td>
                        </tr>