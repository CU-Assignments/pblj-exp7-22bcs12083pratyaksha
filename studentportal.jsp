<form action="AttendanceServlet" method="post">
    Student ID: <input type="text" name="sid" /><br/>
    Subject: <input type="text" name="subject" /><br/>
    Date: <input type="date" name="date" /><br/>
    Status: 
    <select name="status">
        <option>Present</option>
        <option>Absent</option>
    </select><br/>
    <input type="submit" value="Submit Attendance" />
</form>
