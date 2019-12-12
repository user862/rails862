import $ from 'jquery'
import 'select2'
import 'select2/dist/css/select2.css'

$(document).ready ( function () {
  $("#todo_project_id").select2({
    minimumResultsForSearch: -1,width: '360px'
  });

});

