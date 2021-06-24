import { StudentService } from './student.service';
import { NoteService } from './note.service';
import { Component, OnInit} from '@angular/core';
import { Student } from './student';
import { Note } from './note';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { switchMap, tap } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public students: Student[];
  public notes: Note[];
  public note: Note;
  public editStudent: Student;
  public deleteStudent: Student;
  public currentStudent: Student;

  public noteText: string = '';

  constructor(
    private studentService: StudentService,
    private noteService: NoteService
  ) {
      this.notes = [];
      this.note = {
        id: null, 
        createdDate: null, 
        updatedDate: null, 
        noteCode: null, 
        text: null, 
        studentId: null
      };
    }

  ngOnInit() {
    this.getStudents();
  }

  public getStudents(): void {
    this.studentService.getStudents().subscribe(
      (response: Student[]) => {
        this.updateStudentList(response);
      }, 
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  private updateStudentList(list: Student[]) {
    this.students = list;
  }

  public onAddStudent(addForm: NgForm): void {
    document.getElementById('add-student-form')?.click();
    this.studentService.addStudent(addForm.value).subscribe(
      (response: Student) => {
        this.getStudents();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateStudent(student: Student): void {
    this.studentService.updateStudent(student).subscribe(
      (response: Student) => {
        this.getStudents();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteStudent(studentId: number): void {
    this.studentService.deleteStudent(studentId).subscribe(
      (response: void) => {
        this.getStudents();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchStudents(key: string): void {
    const results: Student[] = [];
    for (const student of this.students) {
      if (student.name.toLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
      || student.email.toLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
      || student.city.toLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
      || student.git.toLowerCase().indexOf(key.toLocaleLowerCase()) !== -1
      || student.about.toLowerCase().indexOf(key.toLocaleLowerCase()) !== -1) {
        results.push(student);
      }
    }
    this.students = results;
    if (results.length === 0 || !key) {
      this.getStudents();
    }
  }

  public onDeleteNote (notetId: number, student: Student): void {
    const studentId = student.id;

    this.noteService.deleteNote(notetId).pipe(
      tap(() => this.noteText = ''),
      switchMap(() => this.studentService.getStudents())
    ).subscribe(
      (response: Student[]) => {
        this.updateStudentList(response);
        this.currentStudent = this.students.find((i) => i.id === studentId);
        this.notes = this.currentStudent.notes;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public onCreateNote (text: string, currentStudent: Student): void {
    const studentId = currentStudent.id;

    this.note.studentId = currentStudent.id;
    this.note.text = text;
  
    this.noteService.addNote(this.note).pipe(
      tap(() => this.noteText = ''),
      switchMap((response: Note) => this.studentService.getStudents())
    ).subscribe(
      (response: Student[]) => {
        this.updateStudentList(response);
        this.currentStudent = this.students.find((i) => i.id === studentId);
        this.notes = this.currentStudent.notes;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(student: Student, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addStudentModal')
    }
    else if (mode === 'edit') {
      this.editStudent = student;
      button.setAttribute('data-target', '#updateStudentModal')
    }
    else if (mode === 'delete') {
      this.deleteStudent = student;
      button.setAttribute('data-target', '#deleteStudentModal')
    }
    else if (mode === 'notes') {
      this.currentStudent = student;
      this.notes = this.currentStudent.notes;
      button.setAttribute('data-target', '#notesStudentModal')
    }
    else if (mode === 'about') {
      this.currentStudent = student;
      button.setAttribute('data-target', '#aboutStudentModal')
    }
    container?.appendChild(button);
    button.click();
  } 
}
