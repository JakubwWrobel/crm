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

        /*
          let yesBtn = $("#yesButton")
          yesBtn.on("click", hideModal)
          function hideModal() {
          $(this).data("dismiss", "modal");
                }*/

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
                                alert("Client all cars he owned have been deleted")
                                rowClicked.remove()
                            } else {
                                $("#displayName").html("Client has active orders, please delete order first")
                            }
                        }
                    });

                })
            }
        }
    }
    let tabDelete = $("tr")
    tabDelete.on("click", showModalToDelete)

    /*
        function sendHttpRequest(event) {
            e.preventDefault();
            $.ajax({
                url: "http://localhost:8080/deleteemployee",
                type: "POST",
                dataType: "json",
                data: {"id": 1},
                success: function (data) {
                    if (data.isValid) {
                        $("#displayName").html("COrrect")
                        console.log("correct_LOG")
                    } else {
                        $("#displayName").html("Please correct")
                        alert("Please correct!")
                    }

                }
            });
            return false;


            //
            //takes global from showModalToDeleteEm
            // let posting = $.post("/deleteemployee", {id: idEm});
            // posting.done(function (data) {
            //     alert("DATA LOADED " + data)
            // })
        }
    */

    // delBtn.on("click", showModalToDeleteEm)

    /*
        function sendHttpRequest(method, url, data) {
            const promise = new Promise(function (resolve, reject) {
                let xhr = new XMLHttpRequest();
                xhr.open(method, url);
                // Replace parse statement later on
                xhr.responseType = 'json';
                if (data) {
                    xhr.setRequestHeader("Content-Type", "application/json");
                }
                xhr.onload = function () {
                    resolve(xhr.response)
                }
                xhr.send(JSON.stringify(data));
            });
            return promise;
        }

        function deleteEm() {
            sendHttpRequest('GET', "https://reqres.in/api/users").then(responseData => {
                console.log(responseData)
            })
        }

        function sendData() {
            sendHttpRequest('POST', 'http://localhost:8080/deleteemployee', {
                    cll
                }
            )
        }
    */


//between HTML elements;
// localStorage.setItem("start", tabDelete)
// window.document.location = '/deleteemployee'
// https://www.youtube.com/watch?v=GNZg1KRsWuU


    /*XML HTTP REQUEST
    let xmlHttp;

    xmlHttp = new XMLHttpRequest()

    let params = "uname=" + surname2
    console.log(params)
    xmlHttp.open('POST', '/deleteemployee', true)
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xmlHttp.send(params)
    */

})
