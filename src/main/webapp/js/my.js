var body = document.querySelector("body");
var id = body.getAttribute("id");
console.log(id);

let heeader = document.querySelectorAll("nav div.collapse li");
console.log(heeader);

for (let i = 0; i < heeader.length; i++) {
    if(id == heeader[i].getAttribute("id")){
        heeader[i].classList.add("active");
        break;
    }
}


$( "#costOfItems" ).blur(function() {
    this.value = parseFloat(this.value).toFixed(2);
});


