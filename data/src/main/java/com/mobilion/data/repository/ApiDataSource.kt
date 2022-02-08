package com.mobilion.data.repository

import com.mobilion.data.remote.ProjectService
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val projectService: ProjectService
) {
}