# Notify Dump
## What is it?
Notify Dump is a simple Android application that makes information about active notifications accessible over ADB.

## Installation
1. Download and install the latest APK from the [releases page](https://github.com/chrisgavin/notifydump/releases/).
2. Launch the Notify Dump app and enable notification access.

## Usage
### Get All Notifications
```
$ adb shell am broadcast -n me.chrisgavin.notifydump/.NotifyDumpReceiver -a notifydump.get
Broadcasting: Intent { act=notifydump.get flg=0x400000 }
Broadcast completed: result=-1, data="[{"extras":{"android.title":"Pokémon GO Plus","android.reduced.images":true,"android.showChronometer":false,"android.icon":2131165417,"android.text":"The Pokémon fled!","android.progress":0,"android.progressMax":0,"android.showWhen":true,"android.progressIndeterminate":false},"key":"0|com.nianticlabs.pokemongo|2000|null|10192","packageName":"com.nianticlabs.pokemongo"}]"
```
