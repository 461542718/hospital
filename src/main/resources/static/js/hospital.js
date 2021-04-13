function toggle(e) {
    var id =e.getAttribute("data-id");
    $("#spread-"+id).slideToggle();
}
$("#btn_delete").click(function () {
          $("#myModalLabel").text("取消预约");
        $('#myModal').modal();
     });
function yes(e){
    var id=e.getAttribute("data-id");
    //$("#btn_yes"+id).click(function () {
        $("#myModalLabel"+id).text("批准");
        $('#myModal'+id).modal();
    //})
;
}
function no(e){
    var id=e.getAttribute("data-id");
    // $("#btn_no"+id).click(function () {
        $("#myModalLabel1"+id).text("驳回");
        $('#myModal1'+id).modal();
    //})
    ;
}
window.onload = function () {
    var closable=window.localStorage.getItem("closable");
    if(closable){
        window.close();
        window.localStorage.removeItem("closable");
    }
    $("#btn_yes").click(function () {
        $("#myModalLabel").text("批准");
        $('#myModal').modal();
    });
    $("#btn_no").click(function () {
        $("#myModalLabel1").text("驳回");
        $('#myModal1').modal();
    });
}