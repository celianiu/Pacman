var gameLevel = 1;
var currentScreen = 1;
var drawApp;
var keyListener;
var direction = "NULL";
var ghostNum;
var gameStart = false;
var gameOverImageIndex = 0;
function createBackgroundApp(canvas) {
    let c = canvas.getContext("2d");

    let drawWall = function (start, end, color) {
        c.strokeStyle = color;
        c.beginPath();
        c.lineWidth = 4;
        // c.moveTo(start[0] * 1.5, start[1] * 1.5);
        // c.lineTo(end[0] * 1.5 ,end[1] * 1.5);
        c.moveTo(start.x, start.y);
        c.lineTo(end.x, end.y);
        c.stroke();
    };

    let drawDot = function (loc, color, radius) {
        c.fillStyle = color;
        c.beginPath();
        c.arc(loc.x, loc.y, radius, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();
    }


    let clearRect = function (x, y, width, height) {
        c.clearRect(x, y, width, height);
    }

    let clear = function () {
        c.clearRect(0, 0, canvas.width, canvas.height);
    };

    let drawPacman = function (image, x, y) {
        c.drawImage(image, x, y);
    }

    let drawObject = function (img, x, y) {
        c.drawImage(img, x, y);
    }

    return {
        drawWall,
        drawDot,
        clearRect,
        clear,
        drawPacman,
        drawObject: drawObject,
        dims: {height: canvas.height, width: canvas.width}
    }
}

window.onload = function () {
    // display new game page
    $("#landing-page").show();
    $("#new-game-button").click(newGame);
    // bind option btn
    $("#level-button-1").click(1, setLevel);
    $("#level-button-2").click(2, setLevel);
    // bind ghost option btn
    $("#ghost-num-1").click(1, setGhostNum);
    $("#ghost-num-2").click(2, setGhostNum);
    $("#ghost-num-3").click(3, setGhostNum);
    $("#ghost-num-4").click(4, setGhostNum);

    $("#go-back-btn").click(changeScreen);
    $("#gameover-restart").click(gameOver);
    $("#gameover-restart").hide();
    $("#gameover-image").hide();
    $("#gamewin-image").hide();
    preloadSprites();
    drawApp = createBackgroundApp(document.getElementById("canvas"));
}

function gameOver(e) {
    changeScreen();
    changeScreen();
    changeScreen();
}

function newGame() {
    console.log("new game");
    const screens = document.querySelectorAll(".screen");
    screens[0].classList.remove("down");
    screens[0].classList.add("up");
    $("#go-back-btn").show();
    currentScreen = 1;
}

function setLevel(e) {
    console.log("select level", e.data);
    gameLevel = e.data;
    const screens = document.querySelectorAll(".screen");
    screens[1].classList.remove("down");
    screens[1].classList.add("up");
    currentScreen = 2;
}

function setGhostNum(e) {
    console.log("set ghost", e.data);
    ghostNum = e.data;
    const screens = document.querySelectorAll(".screen");
    screens[2].classList.remove("down");
    screens[2].classList.add("up");
    currentScreen = 3;
    $("#before-game-page").hide();
    $("#game-page").show();
    initGame();
}

function changeScreen() {
    console.log("go back", currentScreen)
    const screens = document.querySelectorAll(".screen");
    if (currentScreen === 1) {
        screens[0].classList.remove("up");
        screens[0].classList.add("down");
        $("#go-back-btn").hide();
    } else if (currentScreen === 3) {
        $("#before-game-page").show();
        $("#game-page").hide();
        screens[currentScreen - 1].classList.remove("up");
        screens[currentScreen - 1].classList.add("down");
        currentScreen = currentScreen - 1;
        if (updateIntervalID !== -1) {
            clearInterval(updateIntervalID)
        }
    } else if (currentScreen > 1) {
        screens[currentScreen - 1].classList.remove("up");
        screens[currentScreen - 1].classList.add("down");
        currentScreen = currentScreen - 1;
    }
}

let lifeTemplate='<img class="heart" src="asset/heart.png" width="45px" height="45px">';

function initGameScreen() {
    drawApp.clear();
    $("#gameover-restart").hide();
    $("#gameover-image").hide();
    $("#gamewin-image").hide();
    // $("#gamewin-image").hide();
    $("#pacman-life").empty();
    for(let i = 0; i < 3; i++) {
        $("#pacman-life").append(lifeTemplate);
    }
    updateIntervalID = -1;
}

function initGame() {
    initGameScreen();
    $.post("/reset",{ghostNum: ghostNum, gameLevel: gameLevel}, function (json) {
        keyListener = window.addEventListener("keydown", keydown);
        // for(let i = 0; i <= 12; i++) {
        //     drawApp.drawWall([0, i * 50], [ 1200, i * 50], "white");
        // }
        // for(let i = 0; i <= 24; i++) {
        //     drawApp.drawWall([ i * 50,0], [i * 50, 600], "white");
        // }
        // 边框

        drawApp.drawWall({x: 0, y: 0}, {x: width, y: 0}, "mediumblue");
        drawApp.drawWall({x: 0, y: 0}, {x: 0, y: height}, "mediumblue");
        drawApp.drawWall({x: 0, y: height}, {x: width, y: height}, "mediumblue");
        drawApp.drawWall({x: width, y: height}, {x: width, y: 0}, "mediumblue");
        // draw default image
        let pacman = json.pacman;
        drawApp.drawPacman(pacmanSprites[0][1], pacman.location.x-12, pacman.location.y-12);
        // [color][direction][status]
        drawGhosts(json.ghosts);
        /*drawApp.drawObject(ghostSprites[0][2][1], 9 * 50 + 10, 7 * 50 + 10);
        drawApp.drawObject(ghostSprites[1][0][1], 13 * 50 + 10, 7 * 50 + 10);
        drawApp.drawObject(ghostSprites[2][2][1], 9 * 50 + 10, 8 * 50 + 10);
        drawApp.drawObject(ghostSprites[3][0][1], 13 * 50 + 10, 8 * 50 + 10);*/

        drawWalls(json.mapSetting.wallLineList, "mediumblue");
        drawWalls(json.mapSetting.baseLineList, "green");
        drawWalls(json.mapSetting.exitLineList, "red");
        //画出所有的点
        // console.log(json.mapSetting.dotsList)
        drawDots(json.mapSetting.dotsList, "yellow");
        //画大的点 : bigDotsList
        drawBigDots(json.mapSetting.bigDotsList,"yellow");

        // set highest score
        $("#cur-score").html(0);
        // console.log("highest score", json.highestScore);
        $("#highest-score").html(json.highestScore);

        //画一个水果
        drawFruit(json.mapSetting.fruitList);
        //drawApp.drawObject(fruitSprites[0], 60, 110);
        //drawApp.drawObject(fruitSprites[1], 14 * 50 + 10, 4 * 50 + 10)
        $("#breathing-ntf").show();
    }, "json");
}

let updateIntervalID = -1;
function update() {
    $.post("/move", {direction : direction},function (json) {
        drawApp.clear();
        /* rawApp.drawWall({x: 0, y: 0}, {x: width, y: 0}, "mediumblue");
        drawApp.drawWall({x: 0, y: 0}, {x: 0, y: height}, "mediumblue");
        drawApp.drawWall({x: 0, y: height}, {x: width, y: height}, "mediumblue");
        drawApp.drawWall({x: width, y: height}, {x: width, y: 0}, "mediumblue");*/

        if (json.isGameOver) {
            console.log("game over");
            drawApp.drawPacman(deadPacmanSprites[gameOverImageIndex], 610, 310);
            gameOverImageIndex++;
            if (gameOverImageIndex === 12) {
                gameOverImageIndex = 0;
                clearInterval(updateIntervalID);
                updateIntervalID = -1;
                $("#gameover-restart").show();
                $("#gameover-image").show();
            }
            return;
        }
        if (json.isGameWin) {
            clearInterval(updateIntervalID);
            updateIntervalID = -1;
            $("#gameover-restart").show();
            $("#gamewin-image").show();
            return;
        }
        // draw life
        $("#pacman-life").empty();
        for(let i = 0; i < json.life; i++) {
            $("#pacman-life").append(lifeTemplate);
        }

        // handle ghost collide
        // console.log(json.collideGhost);
        if (json.collideGhost) {
            direction = "NULL";
        }

        // change score
        $("#cur-score").html(json.score);
        $("#highest-score").html(json.highestScore);
        // 边框
        drawApp.drawWall({x: 0, y: 0}, {x: width, y: 0}, "mediumblue");
        drawApp.drawWall({x: 0, y: 0}, {x: 0, y: height}, "mediumblue");
        drawApp.drawWall({x: 0, y: height}, {x: width, y: height}, "mediumblue");
        drawApp.drawWall({x: width, y: height}, {x: width, y: 0}, "mediumblue");
        drawWalls(json.mapSetting.wallLineList, "mediumblue");
        drawWalls(json.mapSetting.baseLineList, "green");
        drawWalls(json.mapSetting.exitLineList, "red");
        // draw default image
        let pacman = json.pacman;
        let pacdirection;
        switch (pacman.directionEnumType) {
            case "LEFT":
                pacdirection = 0;
                break;
            case "UP":
                pacdirection = 1;
                break;
            case "RIGHT":
                pacdirection = 2;
                break;
            case "DOWN":
                pacdirection = 3;
                break;
        }
        let pacmanIndex = json.pacman.flash;
        drawApp.drawPacman(pacmanSprites[pacdirection][pacmanIndex], pacman.location.x-12, pacman.location.y-12);
        // 画所有ghost的图片  有3种维度 [color][direction][status]
        drawGhosts(json.ghosts)
        /*drawApp.drawObject(ghostSprites[0][2][1], 9 * 50 + 10, 7 * 50 + 10);
        drawApp.drawObject(ghostSprites[1][0][1], 13 * 50 + 10, 7 * 50 + 10);
        drawApp.drawObject(ghostSprites[2][2][1], 9 * 50 + 10, 8 * 50 + 10);
        drawApp.drawObject(ghostSprites[3][0][1], 13 * 50 + 10, 8 * 50 + 10);*/
        //draw line, update不需要再重新画线了
        /*drawWalls(json.wallLineList, "mediumblue");
        drawWalls(json.baseLineList, "green");
        drawWalls(json.exitLineList, "red");*/

        //画出所有的点
        drawDots(json.mapSetting.dotsList, "yellow");
        //bigDotsList
        drawBigDots(json.mapSetting.bigDotsList,"yellow");
        //画大的点
        /* drawApp.drawDot({x: 75, y: 25}, "yellow", 12);
        drawApp.drawDot({x: 75, y: 575}, "yellow", 12);
        drawApp.drawDot({x: 1275, y: 25}, "yellow", 12);
        drawApp.drawDot({x: 1275, y: 625}, "yellow", 12);*/
        //画一个水果
        drawFruit(json.mapSetting.fruitList);
       /* drawApp.drawObject(fruitSprites[0], 60, 110);
        drawApp.drawObject(fruitSprites[1], 14 * 50 + 10, 4 * 50 + 10)*/
        //updateIntervalID = setInterval(update,100);
    },"json");

}

function drawWalls(wallList, color) {
    for (let i = 0; i < wallList.length; i++) {
        let line = wallList[i];
        drawApp.drawWall(line[0], line[1], color);
    }
}

function drawFruit(fruitList) {
    for (let i = 0; i < fruitList.length; i++) {
        let fruit = fruitList[i];
        drawApp.drawObject(fruitSprites[fruit.type], fruit.location.x- 12 , fruit.location.y - 12);
    }
}

function drawGhosts(ghosts) {
    for (let i = 0; i < ghosts.length; i++) {
        let ghost = ghosts[i];
        let direction;
        switch (ghost.directionEnumType) {
            case "LEFT":
                direction = 0;
                break;
            case "UP":
                direction = 1;
                break;
            case "RIGHT":
                direction = 2;
                break;
            case "DOWN":
                direction = 3;
                break;
        }
        // console.log(ghost.status);
        switch (ghost.status) {
            case "NORMAL":
                drawApp.drawObject(ghostSprites[ghost.color][direction][ghost.flash], ghost.location.x- 12 , ghost.location.y - 12);
                break;
            case "BLUE":
                drawApp.drawObject(flashingGhostSprites[0][ghost.flash], ghost.location.x- 12 , ghost.location.y - 12);
                break;
            case "TWO_EYES":
                drawApp.drawObject(deadGhostSprites[direction], ghost.location.x- 12 , ghost.location.y - 12);
                break;
            case "DIZZY":
                drawApp.drawObject(flashingGhostSprites[1][ghost.flash], ghost.location.x- 12 , ghost.location.y - 12);
                break;
        }

    }
}

function drawDots(dotList, color) {
    for (let i = 0; i < dotList.length; i++) {
        let loc = dotList[i];
        drawApp.drawDot(loc, color, 4)
    }
}

function drawBigDots(dotList, color) {
    for (let i = 0; i < dotList.length; i++) {
        let loc = dotList[i];
        drawApp.drawDot(loc, color, 12)
    }
}



let keydown = function (e) {
    // LEFT: 37,
    // UP: 38,
    // RIGHT: 39,
    // DOWN: 40,
    console.log("press", e.keyCode);
    if (updateIntervalID === -1) {
        updateIntervalID = setInterval(update,100);
        $("#breathing-ntf").hide();
    }
    if (gameStart === false) {
        gameStart = true;
    }
    if (e.keyCode == 37 ) {
        direction = "LEFT";
    } else if (e.keyCode == 38 ) {
        direction = "UP";
    } else if (e.keyCode == 39 ) {
        direction = "RIGHT"
    } else if (e.keyCode == 40 ) {
        direction = "DOWN";
    } else if (e.keyCode == 13 && gameOver == true) {
        // gameOver = false;
    }
}

