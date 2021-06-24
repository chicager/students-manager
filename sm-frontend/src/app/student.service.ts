import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Student } from './student';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiServerUrl}/students`);
  }

  public addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(`${this.apiServerUrl}/students`, student);
  }

  public updateStudent(student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiServerUrl}/students/${student.id}`, student);
  }

  public deleteStudent(studentId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/students/${studentId}`);
  }
}
