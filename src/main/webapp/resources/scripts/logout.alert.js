$(".logoutLink").click(function () {
    var str = '<fmt:message key="header.log.out.alert"/>';
    $("#modalBodyWarningMessageLogout").html(str);
});