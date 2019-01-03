var global='this is global variable';
function globalFunction() {
    var localVariable='this is local variable'
    console.info(global)
    console.info(localVariable)
}

globalFunction()
console.info(localVariable)