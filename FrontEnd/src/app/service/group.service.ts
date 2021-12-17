import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Group } from "../interface/group";

@Injectable()
export class GroupService{
    // add /groups path
    private apiBaseUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) {}

    // public create(group: Group): Observable<Group> {
    //     return this.http.post<Group>(`${this.apiBaseUrl}/groups`, group);
    // }

    // public read(id: number): Observable<Group> {
    //     return this.http.get<Group>(`${this.apiBaseUrl}/groups/${id}`);
    // }

    public readAll(): Observable<Group[]> {
        return this.http.get<Group[]>(`${this.apiBaseUrl}/groups`);
    }

    // public update(group: Group): Observable<Group> {
    //     return this.http.put<Group>(`${this.apiBaseUrl}/groups`, group);
    // }

    // public delete(id: number): Observable<void> {
    //     return this.http.delete<void>(`${this.apiBaseUrl}/groups/${id}`);
    // }
}
