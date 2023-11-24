package main

import (
    "net/http"
    "github.com/gin-gonic/gin"
)

func getData(c *gin.Context) {
    c.IndentedJSON(http.StatusOK, gin.H{"foo": "bar"})
}

func main() {
    r := gin.Default()
    r.GET("/ping", getData)

    r.Run() // listen and serve on 0.0.0.0:8080
}
