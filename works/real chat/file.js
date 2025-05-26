

const socket = io()
const form = document.getElementById("form")
const input = document.getElementById("input")
const messages = document.getElementById("messages")

form.addEventListener("submit", (e) => {
    e.preventDefault()
    if (input.value) {
        socket.emit("messagerie",input.value)
        input.value = ''
    }
});
socket.on('messagerie ', (msg)=>{ 
    const item = document.createElement('li')
    console.log("le message recu :" ,msg);
    item.textContent = msg;
    messages.appendChild(item)
    window.scrollTo(0,document.body.scrollHeight)
})