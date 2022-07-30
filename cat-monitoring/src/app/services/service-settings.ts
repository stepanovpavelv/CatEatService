export class CommonServiceSettings {
    private static readonly baseUrl: string = '/gateway/private/api/v1';
    
    static readonly loginUrl: string = CommonServiceSettings.baseUrl + '/user/login';
    static readonly indicationDatesUrl = CommonServiceSettings.baseUrl + '/entries';
}