

$(document).ready ( function () {
  
  $('#submit').click(function(){
    event.preventDefault();
    $("#todo_create_form").submit();
    });

  $('#add_todo').click(function(){
     event.preventDefault();
     $(".todo_block").show();
    });

  $('#hide_form').click(function(){
    event.preventDefault();
    $(".todo_block").hide();
    });
  
   $(' input').iCheck({
    checkboxClass: 'icheckbox_square-blue',
    radioClass: 'iradio_square-blue',
    increaseArea: '20%'
    });

  $("input:checkbox:checked").parents()
        .children("span")
        .css("text-decoration","line-through");

  $('input').on('ifChanged',function(){
    event.preventDefault();
    $(this).parents()
      .children("span")
      .css("text-decoration","line-through")
    $(this).wrap('<form id="todo_update_form" action="/todos/update" accept-charset="UTF-8" method="post">')
      .hide()
      .prop("type","number")
      .val($(this).prop("id"))
      .closest("form").submit();
  });
  $('span').on('click',function(){
    var input = $(this).parents()
      .children("div")
      .children("input")
    $(input)
      .iCheck('uncheck')
      .wrap('<form id="todo_update_form" action="/todos/update" accept-charset="UTF-8" method="post">')
      .hide()
      .prop("type","number")
      .val($(input).prop("id"))
      .closest("form").submit();
  });
});

