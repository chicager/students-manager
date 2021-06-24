import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Note } from './note';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getNotes(studentId: number): Observable<Note[]> {
    return this.http.get<Note[]>(`${this.apiServerUrl}/student/${studentId}`);
  }

  public addNote(note: Note): Observable<Note> {
    return this.http.post<Note>(`${this.apiServerUrl}/notes`, note);
  }

  public updateNote(note: Note): Observable<Note> {
    return this.http.put<Note>(`${this.apiServerUrl}/notes/${note.id}`, note);
  }

  public deleteNote(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/notes/${noteId}`);
  }
}
