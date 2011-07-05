package com.immani.mydwich
import java.awt.Font
import java.awt.Color
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.springframework.context.*


class ImageService implements ApplicationContextAware {
    def burningImageService
    static transactional = true
    ApplicationContext applicationContext

    def resizeandthumb(Picture pictureInstance, CommonsMultipartFile f, String folder) {
        if (f.getOriginalFilename().substring(f.getOriginalFilename().indexOf('.')) == ".gif" ){
            pictureInstance.filename = f.getOriginalFilename().substring(0 , f.getOriginalFilename().indexOf('.')) + '.jpg'
        }
        else{
            pictureInstance.filename = f.getOriginalFilename()
        }

        pictureInstance.contentType = f.getContentType()
        pictureInstance.file = f.getBytes()
        def watermark = applicationContext.getResource("/images/burn.png").getFile().toString()
        def originalFileName

        burningImageService.doWith(f, folder)
        .execute {
            it.scaleApproximate(1024, 768)
            originalFileName = it.watermark(watermark, ['top':10, 'left': 10])
        }
        .execute ('thumb_' + (pictureInstance.filename.substring(0, pictureInstance.filename.indexOf('.'))) , {
            it.scaleAccurate(200, 200)
        })
        .execute {img ->
                        img.text(Color.WHITE, new Font('Arial', Font.PLAIN, 8), {
                            it.write("Copyright Immani", 10, 10)
                        })
                   }
    }
}
