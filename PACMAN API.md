PACMAN API

endpoint:

/update

request:

```
{
	"direction":  "L", // "R", "U", "D"
}
```

response:

```json
{
wallsinfo:[],// implemented
coinsInfo:[], // implemented
"bigDotsList": [{}]
"pacman": {
  "loc": { // maybe different
    "x": ,
    "y": 
  },
  status: 1 // which status (n = n % 2)
  direction: "L", // "R", "U", "D"
},
"fruitsList": [
  // can implement as same as coins info
  // should include food type
  {
    loc: "", 
    type: 1 // 1 - 3
  }
],
"ghosts": [
  {
    "color": "red", // pink, orange, blue, red
    "loc": {},
    "status": "normal", // normal dizzy justEyes
    "flash": 0 // 0不闪，1 闪白色
    "direction": "L", // "R", "U", "D"
  }
],
"gameOver": false,
"score": INT,
"highestScore": INT,
"life": 3 // 1-3
}
```



endpoint: 

/init

request: 

```json
{
  "numGhosts": INT,
  "difficulty": 1,//1 or 2
}
```

response:

Same as update