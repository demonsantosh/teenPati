package com.hamro.teenpatigame.datas

class ListData {
    var id: String? = null
        private set
    var name: String? = null
        private set
    var movie: String? = null
        private set

    constructor() {}
    constructor(id: String?, name: String?, movie: String?) {
        this.id = id
        this.name = name
        this.movie = movie
    }

}