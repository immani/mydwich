package com.immani.mydwich
import org.springframework.web.servlet.support.RequestContextUtils as RCU


class DisplayTagLib {

    static namespace = "mydwich"
    static supportedLocale = ["fr","nl","en"]
    static defaultLocale = Locale.ENGLISH


    def disploc = { attrs, body ->

        def instanceValue = attrs.instanceValue
        def property = attrs.property

        if (!instanceValue) throw new IllegalArgumentException("[instanceValue] attribute must be specified to for <mydwich:disploc>!")
        if (!property) throw new IllegalArgumentException("[property] attribute must be specified to for <mydwich:disploc>!")

        def locale = RCU.getLocale(request)

        if (!supportedLocale.contains(locale.language)){
            locale = defaultLocale;
        }

        property = property + "_" + locale.language;
        out << instanceValue."$property";
    }

    def disarrayploc = { attrs, body ->

        def instanceValue = attrs.instanceValue
        def property = attrs.property

        if (instanceValue == null) throw new IllegalArgumentException("[instanceValue] attribute must not be null for <mydwich:disarrayploc>!")
        if (!property) throw new IllegalArgumentException("[property] attribute must be specified to for <mydwich:disparrayloc>!")

        def locale = RCU.getLocale(request)

        if (!supportedLocale.contains(locale.language)){
            locale = defaultLocale;
        }
        if (instanceValue.size() == 0){
        out << ""
        }
        else{
                property = property + "_" + locale.language;
        out << instanceValue."$property".join(', ');
        }
    }


    def sortloccol = { attrs ->
        def writer = out
        if (!attrs.property) {
            throwTagError("Tag [sortloccol] is missing required attribute [property]")
        }

        if (!attrs.title && !attrs.titleKey) {
            throwTagError("Tag [sortloccol] is missing required attribute [title] or [titleKey]")
        }

        def locale = RCU.getLocale(request)

        def property = attrs.remove("property")

        if (!supportedLocale.contains(locale.language)){
            locale = defaultLocale;
        }

        property = property + "_" + locale.language;

        def action = attrs.action ? attrs.remove("action") : (actionName ?: "list")

        def defaultOrder = attrs.remove("defaultOrder")
        if (defaultOrder != "desc") defaultOrder = "asc"

        // current sorting property and order
        def sort = params.sort
        def order = params.order

        // add sorting property and params to link params
        def linkParams = [:]
        if (params.id) linkParams.put("id",params.id)
        if (attrs.params) linkParams.putAll(attrs.remove("params"))
        linkParams.sort = property

        // determine and add sorting order for this column to link params
        attrs.class = (attrs.class ? "${attrs.class} sortable" : "sortable")
        if (property == sort) {
            attrs.class = attrs.class + " sorted " + order
            if (order == "asc") {
                linkParams.order = "desc"
            }
            else {
                linkParams.order = "asc"
            }
        }
        else {
            linkParams.order = defaultOrder
        }

        // determine column title
        def title = attrs.remove("title")
        def titleKey = attrs.remove("titleKey")
        if (titleKey) {
            if (!title) title = titleKey
            def messageSource = grailsAttributes.messageSource
            title = messageSource.getMessage(titleKey, null, title, RCU.getLocale(request))
        }

        writer << "<th "
        // process remaining attributes
        attrs.each { k, v ->
            writer << "${k}=\"${v.encodeAsHTML()}\" "
        }
        writer << ">${link(action:action, params:linkParams) { title }}</th>"
    }


}
