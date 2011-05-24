<script type="text/javascript">

 updateConfigurationMap({

   noResultError : '${message(code:'project.noResultError')}.encodeAsHTML()',

   rangeError : '${message(code:'project.rangeError', args:[50, 100]).encodeAsHTML()}',

   ajaxCallUrl : '${createLink(controller:'myController', action: 'myAction')}',

   deleteImagePath : '${resource(dir:'images', file:'myimage.png')}'

 });

</script>