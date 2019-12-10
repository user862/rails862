$(document).ready ( function () {
  $('#submit').click(function(){
    event.preventDefault();
    $(this).closest("form").submit()
    });
  $('#add_todo').click(function(){
     event.preventDefault();
     $(".todo_block").show()
    });
  $('#hide_form').click(function(){
    event.preventDefault();
    $(".todo_block").hide();
    });
});
