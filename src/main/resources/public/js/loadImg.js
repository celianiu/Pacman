
/**
 * Preload images to speed up performance.
 */
function preloadSprites() {
    window.pacmanSprites = [[loadImg("asset/sprites/PacmanL1.png"), loadImg("asset/sprites/PacmanL2.png")],
        [loadImg("asset/sprites/PacmanU1.png"), loadImg("asset/sprites/PacmanU2.png")],
        [loadImg("asset/sprites/PacmanR1.png"), loadImg("asset/sprites/PacmanR2.png")],
        [loadImg("asset/sprites/PacmanD1.png"), loadImg("asset/sprites/PacmanD2.png")]];

    window.deadPacmanSprites = [loadImg("asset/sprites/PacmanClosed.png"),
        loadImg("asset/sprites/DeadPacman1.png"),
        loadImg("asset/sprites/DeadPacman2.png"),
        loadImg("asset/sprites/DeadPacman3.png"),
        loadImg("asset/sprites/DeadPacman4.png"),
        loadImg("asset/sprites/DeadPacman5.png"),
        loadImg("asset/sprites/DeadPacman6.png"),
        loadImg("asset/sprites/DeadPacman7.png"),
        loadImg("asset/sprites/DeadPacman8.png"),
        loadImg("asset/sprites/DeadPacman9.png"),
        loadImg("asset/sprites/DeadPacman10.png"),
        loadImg("asset/sprites/DeadPacman11.png"),
        loadImg("asset/sprites/DeadPacman12.png")];

    window.ghostSprites = [[[loadImg("asset/sprites/RL1.png"), loadImg("asset/sprites/RL2.png")],
        [loadImg("asset/sprites/RU1.png"), loadImg("asset/sprites/RU2.png")],
        [loadImg("asset/sprites/RR1.png"), loadImg("asset/sprites/RR2.png")],
        [loadImg("asset/sprites/RD1.png"), loadImg("asset/sprites/RD2.png")]],
        [[loadImg("asset/sprites/BL1.png"), loadImg("asset/sprites/BL2.png")],
            [loadImg("asset/sprites/BU1.png"), loadImg("asset/sprites/BU2.png")],
            [loadImg("asset/sprites/BR1.png"), loadImg("asset/sprites/BR2.png")],
            [loadImg("asset/sprites/BD1.png"), loadImg("asset/sprites/BD2.png")]],
        [[loadImg("asset/sprites/PL1.png"), loadImg("asset/sprites/PL2.png")],
            [loadImg("asset/sprites/PU1.png"), loadImg("asset/sprites/PU2.png")],
            [loadImg("asset/sprites/PR1.png"), loadImg("asset/sprites/PR2.png")],
            [loadImg("asset/sprites/PD1.png"), loadImg("asset/sprites/PD2.png")]],
        [[loadImg("asset/sprites/OL1.png"), loadImg("asset/sprites/OL2.png")],
            [loadImg("asset/sprites/OU1.png"), loadImg("asset/sprites/OU2.png")],
            [loadImg("asset/sprites/OR1.png"), loadImg("asset/sprites/OR2.png")],
            [loadImg("asset/sprites/OD1.png"), loadImg("asset/sprites/OD2.png")]]];

    window.flashingGhostSprites = [[loadImg("asset/sprites/FlashA1.png"), loadImg("asset/sprites/FlashA2.png")],
        [loadImg("asset/sprites/FlashB1.png"), loadImg("asset/sprites/FlashB2.png")]];

    window.deadGhostSprites = [loadImg("asset/sprites/DeadGhostL.png"),
        loadImg("asset/sprites/DeadGhostU.png"),
        loadImg("asset/sprites/DeadGhostR.png"),
        loadImg("asset/sprites/DeadGhostD.png")];

    window.fruitSprites = [loadImg("asset/sprites/Fruit1.png"),
        loadImg("asset/sprites/Fruit2.png"),
        loadImg("asset/sprites/Fruit3.png"),
        loadImg("asset/sprites/Fruit4.png"),
        loadImg("asset/sprites/Fruit5.png"),
        loadImg("asset/sprites/Fruit6.png"),
        loadImg("asset/sprites/Fruit7.png"),
        loadImg("asset/sprites/Fruit8.png"),
        loadImg("asset/sprites/Fruit9.png")];
}

/**
 * Helper function for preloading sprites.
 */
function loadImg(src) {
    let img = new Image();
    img.src = src;
    return img;
}