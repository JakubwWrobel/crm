document.addEventListener("DOMContentLoaded", function () {
        var body = document.querySelector("body");
        id = body.getAttribute("id");
        let heeader = document.querySelectorAll("nav div.collapse li");
        for (let i = 0; i < heeader.length; i++) {
            if (id == heeader[i].getAttribute("id")) {
                heeader[i].classList.add("active");
                break;
            }
        }

        var tabb = document.querySelectorAll("#tabb")

// AUTOINCREMENT
        function into() {
            let increment = 0;
            for (let i = 0; i < tabb.length; i++) {
                tabb[i].firstElementChild.innerHTML = ++increment
            }
        }

        into(tabb);

        let cll = $(".table");
        $(".table").on('mousedown', function (event) {
            event.preventDefault()

        });
        /*    //send emails
            let submitBtn = $("#emailBtn");
            function sendEmails() {
            }
            submitBtn.on("click", sendEmails)*/


        //modal
        $('#myModal').on('shown.bs.modal', function () {
            $('#myInput').trigger('focus')
        })


        function showModalToDelete() {
            let idEl = $(this).find('td').eq(0).html()
            let idEl2 = $(this).find('td').eq(0)
            rowClicked = idEl2.parent('tr')
            let toSend = {'id': idEl}
            let name = $(this).find('td').eq(1).html()
            let name2 = $(this).find('td').eq(2).html()
            let modalBody = $("#deleteModalBody")
            modalBody.html("Are you sure you want to delete: " + name + " " + name2);



            if ($('#yesButton').length > 0) {

                let yesBtn = $("#yesButton")
                if (id === "employee") {
                    yesBtn.on("click", function (event) {
                        event.preventDefault();
                        $.ajax({
                            url: "http://localhost:8080/deleteemployee",
                            type: "POST",
                            dataType: "json",
                            data: toSend,
                            success: function (data) {
                                if (data.isValid) {
                                    alert("Employee has been deleted, all orders have been closed and assigned to owner")
                                    rowClicked.remove()
                                } else {
                                    $("#displayName").html("Employee has active order, please delete order first")
                                }
                            }
                        });
                    })

                } else if (id === "client") {
                    yesBtn.on("click", function (event) {
                        event.preventDefault();
                        $.ajax({
                            url: "http://localhost:8080/deleteclient",
                            type: "POST",
                            dataType: "json",
                            data: toSend,
                            success: function (data) {
                                if (data.isValid) {
                                    alert("Client and all cars he owned have been deleted")
                                    rowClicked.remove()
                                } else {
                                    alert("Client has active orders, please delete order first")
                                    $("#displayName").html("Client has active orders, please delete order first")
                                }
                            }
                        });

                    })
                } else if (id === "car") {
                    yesBtn.on("click", function (event) {
                        event.preventDefault();
                        $.ajax({
                            url: "http://localhost:8080/deletecar",
                            type: "POST",
                            dataType: "json",
                            data: toSend,
                            success: function (data) {
                                if (data.isValid) {
                                    alert("Car and assigned client have been deleted")
                                    rowClicked.remove()
                                } else {
                                    alert("Car is part of unfinished order, please delete order to proceed")
                                    $("#displayName").html("Car is part of unfinished order, please delete order to proceed")
                                }
                            }
                        });

                    })

                } else if (id === "order") {
                    yesBtn.on("click", function (event) {
                        event.preventDefault();
                        $.ajax({
                            url: "http://localhost:8080/deleteorder",
                            type: "POST",
                            dataType: "json",
                            data: toSend,
                            success: function (data) {
                                if (data.isValid) {
                                    alert("Order has been deleted and canceled")
                                    rowClicked.remove()
                                } else {
                                    alert("Order cannot be deleted")
                                    $("#displayName").html("Order cannot be deleted")

                                }
                            }
                        });

                    })
                }
            }
        }

        let tabDelete = $("tr")
        tabDelete.on("click", showModalToDelete)



        //SEARCHING BAR
        let searching = $("#searching");
        let allHrefs = $("#mainNavbar a");

        searching.on("submit", function (event) {
            let findBarVal = $("#findBar").val().toLowerCase();
            for (let i = 0; i < allHrefs.length; i++) {
                if (findBarVal === allHrefs.eq(i).attr('href').substring(1)) {
                    window.location.replace(allHrefs.eq(i).attr('href'))
                }
            }
            event.preventDefault()
        })

        //Work Hour List Show/Hide
        let whlist = $("#whlist tr");
        if(whlist.length < 2){
           whlist.css("display", "none");
        }
    }
)
