/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.busschedule.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "Schedule")
data class BusSchedule(
   @PrimaryKey
   val id: Int = 0,
   val stop_name: String,
   val arrival_time: Int
)

data class BusScheduleDetail(
   val id: Int = 0,
   val stopName: String = "",
   val arrivalTimeInMillis: String = ""
)

fun BusSchedule.toBusScheduleDetail(): BusScheduleDetail = BusScheduleDetail(
   id = id,
   stopName = stop_name,
   arrivalTimeInMillis = this.formatedTime()
)

fun BusSchedule.formatedTime(): String {
   return SimpleDateFormat("h:mm a", Locale.getDefault())
      .format(Date(arrival_time.toLong() * 1000))
}

