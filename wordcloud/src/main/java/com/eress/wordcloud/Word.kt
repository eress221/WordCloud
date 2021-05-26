package com.eress.wordcloud

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Word : Parcelable {

    private var word: String? = null
    private var size: Int = 0
    private var rank: Int = 0
    private var color: String? = null

    constructor(word: String, size: Int, rank: Int) {
        this.word = word
        this.size = size
        this.rank = rank
        val random = Random()
        this.color = String.format("#%02X%02X%02X", random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

    constructor(word: String, size: Int, rank: Int, color: String?) {
        this.word = word
        this.size = size
        this.rank = rank
        this.color = color
    }

    constructor(parcel: Parcel) {
        this.word = parcel.readString()
        this.size = parcel.readInt()
        this.rank = parcel.readInt()
        this.color = parcel.readString()
    }

    fun setWord(word: String) {
        this.word = word
    }

    fun getWord(): String? {
        return word
    }

    fun setSize(size: Int) {
        this.size = size
    }

    fun getSize(): Int {
        return size
    }

    fun setRank(rank: Int) {
        this.rank = rank
    }

    fun getRank(): Int {
        return rank
    }

    fun setColor(color: String) {
        this.color = color
    }

    fun getColor(): String? {
        return color
    }

    companion object CREATOR: Parcelable.Creator<Word> {
        override fun createFromParcel(parcel: Parcel): Word {
            return Word(parcel)
        }

        override fun newArray(size: Int): Array<Word?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(word)
        parcel?.writeInt(size)
        parcel?.writeInt(rank)
        parcel?.writeString(color)
    }



}