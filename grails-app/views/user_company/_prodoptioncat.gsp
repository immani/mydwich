<%@ page import="com.immani.mydwich.ProdOption" %>
<%@ page import="com.immani.mydwich.ProdOptionCategory" %>
                    <g:each in="${productOptionCategories}" var="ProdOptionCat">
                        <tr class="prop">
                            <td valign="top" class="name">
                                <mydwich:disploc instanceValue="${ProdOptionCat}" property="name" />
                            </td>

                            <td valign="top" class="value">
                                <g:if test="${ProdOptionCat.type=='radio'}">
                                    <g:each in="${ProdOptionCat.options}" var="prodOption">
                                        <g:radio name="option.${ProdOptionCat.name_en}" value="${prodOption.id}"/><mydwich:disploc instanceValue="${prodOption}" property="name" /> (${prodOption.price} €)
                                    </g:each>
                                </g:if>
                                <g:else>
                                    <g:each in="${ProdOptionCat.options}" var="prodOption">
                                        <g:checkBox name="option.${ProdOptionCat.name_en}" value="${prodOption.id}" checked=""/><mydwich:disploc instanceValue="${prodOption}" property="name" /> (${prodOption.price} €)
                                    </g:each>

                                </g:else>
                            </td>
                        </tr>
                    </g:each>