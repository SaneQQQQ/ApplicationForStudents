import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../interface/page";
import {Util} from "../util";
import {Student} from "../interface/student";
import {Mark} from "../interface/mark";

@Injectable()
export class StudentService {
  private apiBaseUrl = environment.apiBaseUrl + '/students';

  constructor(private http: HttpClient) {
  }

  public create(student: Student): Observable<Student> {
    return this.http.post<Student>(`${this.apiBaseUrl}`, student);
  }

  public update(student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiBaseUrl}`, student);
  }

  public delete(id: number): Observable<string> {
    return this.http.delete<string>(`${this.apiBaseUrl}/${id}`);
  }

  public read(id: number): Observable<Student> {
    return this.http.get<Student>(`${this.apiBaseUrl}/${id}`);
  }

  public readAll(page: number, pageSize: number, sortBy: string, order: string): Observable<Page<Student>> {
    if (Util.isNullOrEmpty(sortBy, order)) {
      return this.http.get<Page<Student>>(`${this.apiBaseUrl}?page=${page}&page_size=${pageSize}`);
    }
    return this.http.get<Page<Student>>(`${this.apiBaseUrl}?page=${page}&page_size=${pageSize}&sort_by=${sortBy}&order=${order}`);
  }

  public readAllByGroupId(groupId: number, page: number, pageSize: number, sortBy: string, order: string): Observable<Page<Student>> {
    if (Util.isNullOrEmpty(sortBy, order)) {
      return this.http.get<Page<Student>>(`${this.apiBaseUrl}/group/${groupId}?page=${page}&page_size=${pageSize}`);
    }
    return this.http.get<Page<Student>>(`${this.apiBaseUrl}/group/${groupId}?page=${page}&page_size=${pageSize}&sort_by=${sortBy}&order=${order}`);
  }

  public createMark(mark: Mark): Observable<Mark> {
    return this.http.post<Mark>(`${this.apiBaseUrl}/marks`, mark);
  }

  public updateMark(mark: Mark): Observable<Mark> {
    return this.http.put<Mark>(`${this.apiBaseUrl}/marks`, mark);
  }

  public deleteMark(studentId: number, subjectId: number): Observable<string> {
    return this.http.delete<string>(`${this.apiBaseUrl}/${studentId}/marks/${subjectId}`);
  }

  public readAllMarksByStudent(studentId: number, page: number, pageSize: number, sortBy: string, order: string): Observable<Page<Mark>> {
    if (Util.isNullOrEmpty(sortBy, order)) {
      return this.http.get<Page<Mark>>(`${this.apiBaseUrl}/${studentId}/marks?page=${page}&page_size=${pageSize}`);
    }
    return this.http.get<Page<Mark>>(`${this.apiBaseUrl}/${studentId}/marks?page=${page}&page_size=${pageSize}&sort_by=${sortBy}&order=${order}`);
  }

}
