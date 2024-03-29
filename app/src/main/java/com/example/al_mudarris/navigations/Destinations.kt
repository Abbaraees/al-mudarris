package com.example.al_mudarris.navigations

interface Destinations {
    val route: String
    val name: String
}

object Setup:Destinations {
    override val name = "setup"
    override val route = "setup"
}

object Login:Destinations {
    override val name = "login"
    override val route = "login"
}

object Dashboard:Destinations {
    override val name = "dashboard"
    override val route = "dashboard"
}

object Students:Destinations {
    override val name = "students"
    override val route = "students"
}

object StudentInfo:Destinations {
    override val name = "Student Info"
    override val route = "studentInfo"
    val argStudentId = "argStudentId"
}

object AssessmentDest:Destinations {
    override val name = "Assessment Screen"
    override val route = "assessment"
}

object AssessmentDetailDest:Destinations {
    override val name = "Assessment Detail"
    override val route = "assessmentDetail"
    val argAssessmentId = "argAssessmentID"
}

object AddAssessmentScoreDest:Destinations {
    override val name = "Add Assessment Score"
    override val route = "assessmentScore"
    val argAssessmentId = "argAssessmentID"
}
