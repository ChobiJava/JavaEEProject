/**
 * Created by Chobii on 14/09/15.
 */

$(function() {
    $(".ssso a:first").attr('href', "/studentView.xhtml");
});

$(".ssso a:first").onclick(function() {
    window.location.href="../../app/studentView.xhtml";
});
