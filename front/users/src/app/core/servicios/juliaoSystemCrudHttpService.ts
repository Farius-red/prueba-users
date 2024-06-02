import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';




export  class JuliaoSystemCrudHttpService<E> {
    protected http!: HttpClient;
    basePathUrl: string ="";
    
  
    constructor(http: HttpClient){};
  
    findByIdBussines(idBussines: number ):Observable<E[]>{

        return this.http.get<E[]>(this.basePathUrl+"/all/"+idBussines,{},);
    }

    add(entidad: any):Observable<E>{
        
        return  this.http.post<E>(this.basePathUrl+"add",entidad,{});
        
    };

   
    all(id?: string | null, idBussines?: number | null): Observable<E> {
        let params = new HttpParams();
         let headers = new  HttpHeaders();
        if (id) {
          params = params.append('id', id);
        }
        if (idBussines) {
          headers = headers.append('idBussines', String(idBussines));
        }
        return this.http.get<E>(this.basePathUrl + "all", { params,headers });
      }
   
   
  //  remove(itemId: number): Promise<boolean>;
   // static ɵfac: ɵngcc0.ɵɵFactoryDeclaration<JuliaoSystemCrudHttpService<any>, never>;
}