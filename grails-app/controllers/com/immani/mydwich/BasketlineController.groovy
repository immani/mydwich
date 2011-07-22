package com.immani.mydwich

class BasketlineController {

    def show = {
        def basketLineInstance = BasketLine.get(params.id)
        if (!basketLineInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'basketLine.label', default: 'BasketLine'), params.id])}"
            redirect(action: "list")
        }
        else {
            [basketLineInstance: basketLineInstance]
        }
    }
}
