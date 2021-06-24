import { Note } from './note';
export interface Student {
    id: number;
    name: string;
    email: string;
    city: string;
    imageUrl: string;
    notes: Note[]
    phone: number;
    git: string;
    about: string;
    studentCode: string;
}