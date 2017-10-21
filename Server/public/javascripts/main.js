
// firebase
var database = firebase.database();
var ideasRef = database.ref('/ideas');
var keywordsRef = database.ref('/keywords');

// add Article function
function addIdeasArticle(key, keyword, content) {
    var messageArticle = document.createElement("article");
    messageArticle.setAttribute("id", key);
    messageArticle.setAttribute("class", "message is-info");

    var messageHeaderDiv = document.createElement("div");
    messageHeaderDiv.setAttribute("class", "message-header");

    var p = document.createElement("p");
    p.innerHTML = keyword;

    var button = document.createElement("button");
    button.setAttribute("class", "delete");
    button.setAttribute("aria-label", "delete");
    button.setAttribute("onclick", "removeIdeasArticle('" + key + "');");

    var messageBodyDiv = document.createElement("p");
    messageBodyDiv.setAttribute("class", "message-body");
    messageBodyDiv.innerHTML = content.replace(/\n/g, "<br>");

    messageHeaderDiv.appendChild(p);
    messageHeaderDiv.appendChild(button);
    messageArticle.appendChild(messageHeaderDiv);
    messageArticle.appendChild(messageBodyDiv);

    document.getElementById("stream").appendChild(messageArticle);
    document.getElementById("stream").scrollBy(0,10000);
}

// remove Article function
function removeIdeasArticle(key) {
    var deleteArticle = document.getElementById(key);
    while (deleteArticle.firstChild) {
        deleteArticle.removeChild(deleteArticle.firstChild);
    }
    deleteArticle.parentNode.removeChild(deleteArticle);
}

// watching add element
ideasRef.on('child_added', function(data) {
    addIdeasArticle(data.key, data.val().keyword, data.val().content);
});

// watching remove element
ideasRef.on('child_removed', function(data) {
    removeIdeasArticle(data.key);
});

// search keyword function
function searchKeyword() {
    if(window.event.keyCode == 13){
        var keyword = document.getElementById("keyword").value;
        var stream = document.getElementById("stream");
        while (stream.lastChild) {
            stream.removeChild(stream.lastChild);
        }
        database.ref("/keywords/" + keyword).on('child_added', function(data) {
            addIdeasArticle(data.key, data.val().keyword, data.val().content);
        });
        document.getElementById("keyword").value = "";
    }
}
