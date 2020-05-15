document.addEventListener("DOMContentLoaded", function () {
    var body = document.querySelector("body");
    var id = body.getAttribute("id");
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

    let tabDeleteEmployee = $("tr")
    console.log(tabDeleteEmployee)

    function showModaltoDelete() {
        let id = $(this).find('td').eq(0).html()
        let name = $(this).find('td').eq(1).html()
        let surname = $(this).find('td').eq(2).html()
        let employee = $("#employeeDelete")
        employee.html("Are you sure you want to delete: "+ name + " " + surname);
    }

    tabDeleteEmployee.on("click", showModaltoDelete)

    let yesBtn = $("#yesButton")

})
